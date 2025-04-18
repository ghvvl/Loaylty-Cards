import WidgetKit
import App
import UIKit

class IOSWidgetProvider: AppIntentTimelineProvider {
    
    private let widgetController: RootWidgetController = RootWidgetController()
    
    private let defaultLoyaltyCard = CommonLoyaltyCard(name: "cusotm card name", data: "https://www.google.com", codeType: CommonLoyaltyCardCodeType.qrCode, cardColor: Int32(0xFF0000))
    
    private func makeDefaultWidgetModel() -> IOSWidgetModel {
        return IOSWidgetModel(card: IOSWidgetCardModel(cardImage: UIImage(data: widgetController.mapToNSData(card: defaultLoyaltyCard))!, cardName: defaultLoyaltyCard.name), date: Date(), deeplink: URL(string: "https://www.google.com")!)
    }
    
    func placeholder(in context: Context) -> IOSWidgetModel {
        return makeDefaultWidgetModel()
    }
    
    func snapshot(for configuration: IOSWidgetIntent, in context: Context) async -> IOSWidgetModel {
        return makeDefaultWidgetModel()
    }
    
    func timeline(for configuration: IOSWidgetIntent, in context: Context) async -> Timeline<IOSWidgetModel> {
        if context.isPreview {
            return Timeline(entries: [makeDefaultWidgetModel()], policy: .never)
        }
        
        guard let widgetId = configuration.widgetId, !widgetId.isEmpty else {
            return Timeline(entries: [makeDefaultWidgetModel()], policy: .never)
        }
        
        try! await widgetController.createWidgetState(widgetId: widgetId)
        let currentWidgetState: CommonWidgetState = try! await widgetController.getCurrentWidgetState(widgetId: widgetId)
        guard let card = currentWidgetState.widgetCards.first else {
            return Timeline(entries: [makeDefaultWidgetModel()], policy: .never)
        }
        
        let entry  = IOSWidgetModel(
            card: IOSWidgetCardModel(cardImage: UIImage(data: widgetController.mapToNSData(card: card))!, cardName: card.name),
            date: Date(),
            deeplink: URL(string: widgetController.createDeeplinkForCard(card: currentWidgetState.widgetCards.first!))!
        )
        return Timeline(entries: [entry], policy: .never)
    }
    
}

import WidgetKit
import App
import UIKit

class IOSWidgetProvider: AppIntentTimelineProvider {
    
    private let widgetController: RootWidgetController = RootWidgetController()
    
    private let defaultLoyaltyCard = CommonLoyaltyCard(
        name: String(localized: LocalizedStringResource("default_widget_name", comment: "card name for default card")),
        data: "https://www.google.com",
        codeType: CommonLoyaltyCardCodeType.qrCode,
        cardColor: Int32(0xFF0000))
    
    private func makeDefaultWidgetModel(
        widgetIdWasSettled: Bool = true,
        cardWasSettled: Bool = true,
        widgetId: String = "",
        widgetDeeplink: String = "https://www.google.com"
    ) -> IOSWidgetModel {
        return IOSWidgetModel(
            widgetIdWasSettled: widgetIdWasSettled,
            cardWasSettled: cardWasSettled,
            widgetId: widgetId,
            card: IOSWidgetCardModel(cardImage: UIImage(data: widgetController.mapToNSData(card: defaultLoyaltyCard))!, cardName: defaultLoyaltyCard.name),
            date: Date(),
            cardDeeplink: URL(string: "https://www.google.com")!,
            widgetDeeplink: URL(string: widgetDeeplink)!
        )
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
            return Timeline(entries: [makeDefaultWidgetModel(widgetIdWasSettled: false)], policy: .never)
        }
        
        try! await widgetController.createWidgetState(widgetId: widgetId)
        let currentWidgetState: CommonWidgetState = try! await widgetController.getCurrentWidgetState(widgetId: widgetId)
        guard let card = currentWidgetState.widgetCards.first else {
            return Timeline(entries: [makeDefaultWidgetModel(cardWasSettled: false, widgetId: widgetId,widgetDeeplink: widgetController.createDeeplinkForWidget(widgetId: widgetId))], policy: .never)
        }
        
        let entry  = IOSWidgetModel(
            widgetId: widgetId,
            card: IOSWidgetCardModel(cardImage: UIImage(data: widgetController.mapToNSData(card: card))!, cardName: card.name),
            date: Date(),
            cardDeeplink: URL(string: widgetController.createDeeplinkForCard(card: currentWidgetState.widgetCards.first!))!,
            widgetDeeplink: URL(string: widgetController.createDeeplinkForWidget(widgetId: widgetId))!,
        )
        return Timeline(entries: [entry], policy: .never)
    }
    
}

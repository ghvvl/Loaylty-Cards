import WidgetKit
import Widget

struct IOSWidgetProvider: TimelineProvider {
    let widgetController: RootWidgetController
    
    func placeholder(in context: Context) -> IOSWidgetEntry {
        IOSWidgetEntry(cardName: "CardName", cardData: "CardInfo", date: Date())
   }

    func getSnapshot(in context: Context, completion: @escaping (IOSWidgetEntry) -> Void) {
        completion(
            IOSWidgetEntry(cardName: "CardName", cardData: "CardInfo", date: Date())
        )
    }
    
    func getTimeline(in context: Context, completion: @escaping (Timeline<IOSWidgetEntry>) -> Void) {
        let cards = widgetController.pickLoyaltyCards()
        let card = cards.first!
        let entry = IOSWidgetEntry(cardName: card.name, cardData: card.data, date: Date())
        completion(
            Timeline(
                entries: [entry],
                policy: .never
            )
        )
    }
}

import WidgetKit

struct IOSWidgetModel: TimelineEntry {
    let widgetIdWasSettled: Bool
    let cardWasSettled: Bool
    let widgetId: String
    let card: IOSWidgetCardModel
    let date: Date
    let cardDeeplink: URL
    let widgetDeeplink: URL
    
    init(widgetIdWasSettled: Bool = true, cardWasSettled: Bool = true, widgetId: String, card: IOSWidgetCardModel, date: Date, cardDeeplink: URL, widgetDeeplink: URL) {
        self.widgetIdWasSettled = widgetIdWasSettled
        self.cardWasSettled = cardWasSettled
        self.widgetId = widgetId
        self.card = card
        self.date = date
        self.cardDeeplink = cardDeeplink
        self.widgetDeeplink = widgetDeeplink
    }
}


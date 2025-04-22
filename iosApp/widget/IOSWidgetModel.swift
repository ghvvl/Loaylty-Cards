import WidgetKit

struct IOSWidgetModel: TimelineEntry {
    var widgetIdWasSettled: Bool = true
    var cardWasSettled: Bool = true
    let widgetId: String
    let card: IOSWidgetCardModel
    let date: Date
    let cardDeeplink: URL
    let widgetDeeplink: URL
}


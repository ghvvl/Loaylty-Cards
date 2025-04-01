import WidgetKit

struct IOSWidgetEntry: TimelineEntry {
    let cards: [IOSWidgetCardEntry]
    let date: Date
    let deeplink: URL
}


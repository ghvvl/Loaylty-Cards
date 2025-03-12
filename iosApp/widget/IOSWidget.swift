import SwiftUI
import WidgetKit
import App

@main
struct IOSWidget: Widget {
    
    var body: some WidgetConfiguration {
        StaticConfiguration(
            kind: "Loyalty cards widget",
            provider: IOSWidgetProvider()
        ) { entry in
            IOSWidgetView(entry: entry).containerBackground(.fill.tertiary, for: .widget)
        }
    }
}

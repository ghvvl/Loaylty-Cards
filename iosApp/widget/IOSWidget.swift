import SwiftUI
import WidgetKit
import App
import Widget

@main
struct IOSWidget: Widget {
    var widgetController = RootWidgetController()

    init () {
        IOSDIKt.doInitKoin()
        widgetController.setCallback(callback: WidgetCenter.shared.reloadAllTimelines)
    }
    
    var body: some WidgetConfiguration {
        StaticConfiguration(
        kind: "Loyalty cards widget",
        provider: IOSWidgetProvider(widgetController: widgetController)
    ) { entry in
            IOSWidgetView(entry: entry).containerBackground(.fill.tertiary, for: .widget)
        }
    }
}

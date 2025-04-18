import SwiftUI
import WidgetKit
import App

@main
struct IOSWidget: Widget {
    
    init(){
        IOSDIKt.startKoin()
    }
    
    var body: some WidgetConfiguration {
        AppIntentConfiguration(
            kind: "Loyalty cards widget",
            intent: IOSWidgetIntent.self,
            provider: IOSWidgetProvider()
        ) { entry in
            IOSWidgetView(entry: entry).containerBackground(.white, for: .widget).widgetURL(entry.deeplink)
        }
    }
}

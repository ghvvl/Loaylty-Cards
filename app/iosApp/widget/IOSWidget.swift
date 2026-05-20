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
            var deeplink: URL? = nil
            if(entry.widgetIdWasSettled) {
                deeplink = entry.widgetDeeplink
            } else if(entry.cardWasSettled) {                
                deeplink = entry.cardDeeplink
            }
            return IOSWidgetView(entry: entry).containerBackground(.white, for: .widget).widgetURL(deeplink)
        }
    }
}

import WidgetKit
import AppIntents
import App

struct IOSWidgetIntent: WidgetConfigurationIntent {
    static let title: LocalizedStringResource = "Widget Configuration"
    static let description = IntentDescription("Unique widget instance configuration")
    
    @Parameter(title: "Unique widgetId")
    var widgetId: String?
}

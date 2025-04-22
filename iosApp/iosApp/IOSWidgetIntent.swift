import WidgetKit
import AppIntents
import App

struct IOSWidgetIntent: WidgetConfigurationIntent {
    static let title: LocalizedStringResource = "Widget Configuration"
    static let description = IntentDescription("Widget configuration with custom id")
    
    @Parameter(title: "Unique widgetId")
    var widgetId: String?
}

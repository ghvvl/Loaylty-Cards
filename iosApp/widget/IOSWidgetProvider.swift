import WidgetKit
import App
import UIKit

class IOSWidgetProvider: TimelineProvider, @unchecked Sendable {
    
    private let defaultEntry = IOSWidgetEntry(cards: [IOSWidgetCardEntry(cardImage: UIImage(), cardName: "cardName")], date: Date())
    
    func placeholder(in context: Context) -> IOSWidgetEntry {
        defaultEntry
    }
    
    func getSnapshot(in context: Context, completion: @escaping (IOSWidgetEntry) -> Void) {
        completion(defaultEntry)
    }
    
    private var task: Task<Void, Never>?
    
    private let widgetController: RootWidgetController
    
    init() {
        IOSDIKt.startKoin()
        widgetController = RootWidgetController()
    }
    
    func getTimeline(in context: Context, completion: @escaping @Sendable (Timeline<IOSWidgetEntry>) -> Void) {
        task?.cancel()
        task = Task {
            do {
                let cards = try await widgetController.current()
                let entry  = IOSWidgetEntry(
                    cards: cards.map{ card in IOSWidgetCardEntry(cardImage: UIImage(data: widgetController.mapToNSData(card: card))!, cardName: card.name) },
                    date: Date())
                completion(Timeline(entries: [entry], policy: .never))
            } catch {
                NSLog("Failed with error: \(error)")
            }
        }
    }
    
    deinit {
        task?.cancel()
        task = nil
    }
}

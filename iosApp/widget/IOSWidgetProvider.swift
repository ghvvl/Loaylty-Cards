import WidgetKit
import App

class IOSWidgetProvider: TimelineProvider, @unchecked Sendable {
    
    func placeholder(in context: Context) -> IOSWidgetEntry {
        IOSWidgetEntry(cardName: "CardName", cardData: "CardInfo", date: Date())
    }
    
    func getSnapshot(in context: Context, completion: @escaping (IOSWidgetEntry) -> Void) {
        completion(
            IOSWidgetEntry(cardName: "CardName", cardData: "CardInfo", date: Date())
        )
    }
    
    private var task: Task<Void, Never>?
    
     private let widgetController: RootWidgetController
    
    init() {
          IOSDIKt.startKoin()
          widgetController = RootWidgetController()
    }
    
    func getTimeline(in context: Context, completion: @escaping @Sendable (Timeline<IOSWidgetEntry>) -> Void) {
        //IOSDIKt.stopKoin()
        if (task != nil){ return }
        //task?.cancel()
        task = Task {
            do {
             //   IOSDIKt.startKoin()
                NSLog("CREAETE")
              //  let widgetController = RootWidgetController()
                let cards = try await widgetController.current()
               // IOSDIKt.stopKoin()
                let entry  = IOSWidgetEntry(cardName: cards.first?.name ?? "", cardData: cards.first?.data ?? "", date: Date())
                completion(Timeline(entries: [entry], policy: .never))
                NSLog("cards received \(cards)")
                task = nil
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

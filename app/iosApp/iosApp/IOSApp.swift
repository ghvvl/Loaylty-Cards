import SwiftUI
import App
import WidgetKit

@main
struct iOSApp: App {
    @UIApplicationDelegateAdaptor(AppDelegate.self)
    var appDelegate: AppDelegate
    
    private let rinku = RinkuIos.init(deepLinkFilter: nil, deepLinkMapper: nil)
    
    init() {
        IOSDIKt.startKoin()
        let widgetController = RootWidgetController()
        widgetController
            .setUpdateAllWidgetsCallback(
                callback: WidgetCenter.shared.reloadAllTimelines
            )
        let localSelf = self
        widgetController.setGetAllWidgetsCallback { kotlinCallback in
            localSelf
                .getAllWidgets(completion: { list in kotlinCallback(list) })
        }
    }
    
    private func getAllWidgets(
        completion: @escaping @Sendable ([String]) -> Void
    ) {
        WidgetCenter.shared.getCurrentConfigurations { result in
            var ids = [String]()
            
            if case .success(let configs) = result {
                ids = configs
                    .compactMap {
                        $0.widgetConfigurationIntent(of: IOSWidgetIntent.self)?.widgetId
                    }
            }
            
            completion(ids)
        }
    }
    
    var body: some Scene {
        WindowGroup {
            RootView(
                componentContext: appDelegate.componentContext,
                backDispatcher: appDelegate.backDispatcher
            )
            .ignoresSafeArea(.all)
            .onOpenURL {
                url in rinku.onDeepLinkReceived(url: url.absoluteString)
            }
        }
    }
}

class AppDelegate : NSObject, UIApplicationDelegate {
    private var stateKeeper = StateKeeperDispatcherKt.StateKeeperDispatcher(
        savedState: nil
    )
    
    let backDispatcher: BackDispatcher = BackDispatcherKt.BackDispatcher()
    
    lazy var componentContext = DefaultComponentContext(
        lifecycle: ApplicationLifecycle(),
        stateKeeper: stateKeeper,
        instanceKeeper: nil,
        backHandler: backDispatcher
    )
    
    func application(_ application: UIApplication, shouldSaveSecureApplicationState coder: NSCoder) -> Bool {
        StateKeeperUtilsKt.save(coder: coder, state: stateKeeper.save())
        return true
    }
    
    func application(_ application: UIApplication, shouldRestoreSecureApplicationState coder: NSCoder) -> Bool {
        stateKeeper =
        StateKeeperDispatcherKt
            .StateKeeperDispatcher(
                savedState: StateKeeperUtilsKt.restore (coder: coder)
            )
        return true
    }
}

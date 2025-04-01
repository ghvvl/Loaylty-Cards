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
        RootWidgetController().setCallback(callback: WidgetCenter.shared.reloadAllTimelines)
    }
    
    var body: some Scene {
        WindowGroup {
            RootView(
                componentContext: appDelegate.componentContext,
                backDispatcher: appDelegate.backDispatcher
            )
            .ignoresSafeArea(.all)
            .onOpenURL { url in rinku.onDeepLinkReceived(url: url.absoluteString) }
        }
    }
}

class AppDelegate : NSObject, UIApplicationDelegate {
    private var stateKeeper = StateKeeperDispatcherKt.StateKeeperDispatcher(savedState: nil)
    
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
        StateKeeperDispatcherKt.StateKeeperDispatcher(savedState: StateKeeperUtilsKt.restore (coder: coder))
        return true
    }
}

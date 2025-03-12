import SwiftUI
import App
import Rinku
import WidgetKit

@main
struct iOSApp: App {
    @UIApplicationDelegateAdaptor(AppDelegate.self)
    var appDelegate: AppDelegate
    
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
    
    private let rinku = RinkuIos.init(deepLinkFilter: nil, deepLinkMapper: nil)
    
    func application(
        _ app: UIApplication,
        open url: URL,
        options: [UIApplication.OpenURLOptionsKey : Any] = [:]
    ) -> Bool {
        rinku.onDeepLinkReceived(url: url.absoluteString)
        return true
    }
    
    func application(
        _ application: UIApplication,
        continue userActivity: NSUserActivity,
        restorationHandler: @escaping([UIUserActivityRestoring]? ) -> Void
    ) -> Bool {
        if userActivity.activityType == NSUserActivityTypeBrowsingWeb, let url = userActivity.webpageURL {
            rinku.onDeepLinkReceived(userActivity: userActivity)
        }
        return true
    }
    
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

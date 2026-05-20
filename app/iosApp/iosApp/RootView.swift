import SwiftUI
import App

struct RootView: UIViewControllerRepresentable {
    let componentContext: ComponentContext
    let backDispatcher: BackDispatcher
    
    func makeUIViewController(context: Context) -> UIViewController {
        let controller = RootViewController(componentContext: componentContext).getUIViewController(backDispatcher: backDispatcher)
        controller.overrideUserInterfaceStyle = .light
        return controller
    }
    
    func updateUIViewController(_ uiViewController: UIViewController, context: Context) {
    }
}

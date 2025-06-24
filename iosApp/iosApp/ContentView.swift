import UIKit
import SwiftUI
import ComposeApp

struct ComposeView: UIViewControllerRepresentable {

    func makeCoordinator() -> UIEventHandlerCoordinator {
         UIEventHandlerCoordinator()
    }

    func makeUIViewController(context: Context) -> UIViewController {
        let vc = MainViewControllerKt.mainViewController()
        context.coordinator.attach(to: vc)
        return vc
    }

    func updateUIViewController(_ uiViewController: UIViewController, context: Context) {}
}

struct ContentView: View {
    var body: some View {
        ComposeView()
                .ignoresSafeArea(.keyboard)
    }
}

class UIEventHandlerCoordinator {

    func attach(to viewController: UIViewController) {
        let platformServices = PlatformServicesProvider().getPlatformServices()

        platformServices.startListeningUiEvents { [weak self] event in
            DispatchQueue.main.async {
                self?.handle(event: event, in: viewController)
            }
        }
    }

    private func handle(event: Any, in viewController: UIViewController) {
        switch event {
            case let toastEvent as PlatformUiActions.ShowToast:
                showDialog(title: nil, message: toastEvent.message, in: viewController)
            case let dialogEvent as PlatformUiActions.ShowDialog:
                showDialog(title: dialogEvent.title, message: dialogEvent.message, in: viewController)
            default:
                break
        }
    }

    private func showDialog(title: String?, message: String, in viewController: UIViewController) {
        let alert = UIAlertController(title: title, message: message, preferredStyle: .alert)
        alert.addAction(UIAlertAction(title: "OK", style: .default))
        viewController.present(alert, animated: true)
    }
}




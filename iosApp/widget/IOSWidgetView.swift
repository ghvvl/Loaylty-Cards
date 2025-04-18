import SwiftUI
import Foundation

struct IOSWidgetView : View {
    var entry: IOSWidgetModel
    
    var body: some View {
        Link(destination: entry.deeplink) {
            VStack(spacing: 8) {
                Text(entry.card.cardName)
                    .font(.caption)
                    .fontWeight(.medium)
                    .foregroundColor(.black)
                    .multilineTextAlignment(.center)
                    .lineLimit(1)
                
                Image(uiImage: entry.card.cardImage)
                    .resizable()
                    .aspectRatio(contentMode: .fit)
                
            }
        }
    }
}

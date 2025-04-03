import SwiftUI
import Foundation

struct IOSWidgetView : View {
    var entry: IOSWidgetEntry
    
    var body: some View {
        // TODO: fix it later
        let currentCard = entry.cards[0]
        
        Link(destination: entry.deeplink) {
            VStack(spacing: 8) {
                Text(currentCard.cardName)
                    .font(.caption)
                    .fontWeight(.medium)
                    .foregroundColor(.black)
                    .multilineTextAlignment(.center)
                    .lineLimit(1)
                
                Image(uiImage: currentCard.cardImage)
                    .resizable()
                    .aspectRatio(contentMode: .fit)
                
            }
        }
    }
}

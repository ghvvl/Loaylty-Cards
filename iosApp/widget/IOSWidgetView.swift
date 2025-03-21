import SwiftUI
import Foundation

struct IOSWidgetView : View {
    var entry: IOSWidgetEntry
    
    var body: some View {
      //  ScrollView(.horizontal, showsIndicators: true) {
            HStack {
                ForEach(entry.cards, id: \.cardName) { card in
                    Image(uiImage: card.cardImage)
                        .resizable()
                        .aspectRatio(contentMode: .fit)
                }
            }
      //  }
    }
}

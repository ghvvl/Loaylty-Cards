import SwiftUI

struct IOSWidgetView : View {
    var entry: IOSWidgetEntry
    
    var body: some View {
        Text("Time:")
        Text(entry.cardName)
        
        Text("Favorite Emoji:")
        Text(entry.cardData)
    }
}

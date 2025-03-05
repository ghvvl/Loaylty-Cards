import SwiftUI

struct IOSWidgetView : View {
    var entry: IOSWidgetEntry

    var body: some View {
        Text("Time:")
        Text(entry.date, style: .time)

        Text("Favorite Emoji:")
        Text(entry.cardData + entry.cardName)
    }
}

import SwiftUI
import Foundation

struct IOSWidgetView : View {
    var entry: IOSWidgetModel
    
    var body: some View {
        if(!entry.widgetIdWasSettled) {
            Text("widget_without_id_title")
                .font(.caption)
                .fontWeight(.medium)
                .foregroundColor(.black)
                .multilineTextAlignment(.center)
        } else if (!entry.cardWasSettled) {
            Link(destination: entry.widgetDeeplink) {
                Text(String(format: NSLocalizedString("widget_without_card_attached_title", comment: "title for widet without attached card"), entry.widgetId))
                    .font(.caption)
                    .fontWeight(.medium)
                    .foregroundColor(.black)
                    .multilineTextAlignment(.center)
            }
        } else {
            ZStack {
                Link(destination: entry.widgetDeeplink) {
                    Image(systemName: "gear")
                        .font(.system(size: 24, weight: .regular))
                        .foregroundColor(Color(.black))
                        .frame(width: 32, height: 32)
                }
                .frame(
                    maxWidth: .infinity,
                    maxHeight: .infinity,
                    alignment: .topTrailing
                )
                
                Link(destination: entry.cardDeeplink) {
                    VStack(spacing: 16) {
                        Text(entry.card.cardName)
                            .font(.caption)
                            .fontWeight(.medium)
                            .foregroundColor(.black)
                            .multilineTextAlignment(.center)
                            .lineLimit(1)
                            .padding(.top, 8)
                        
                        Image(uiImage: entry.card.cardImage)
                            .resizable()
                            .aspectRatio(contentMode: .fit)
                    }
                }
            }
        }
    }
}

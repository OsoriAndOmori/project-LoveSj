import SwiftUI
import WidgetKit

private let widgetGroupId = "group.com.osori.lovesj"
private let widgetTitleKey = "widget_title"
private let widgetItemsKey = "widget_items_json"

struct Provider: TimelineProvider {
  func placeholder(in context: Context) -> LoveSjEntry {
    LoveSjEntry(
      date: Date(),
      title: "우리 일정",
      items: [
        "연애 시작일  2022. 10. 9.  D+1283",
        "결혼기념일  2024. 11. 2.  D+529",
        "수진  2026. 6. 23.  D-71",
      ]
    )
  }

  func getSnapshot(in context: Context, completion: @escaping (LoveSjEntry) -> Void) {
    completion(loadEntry())
  }

  func getTimeline(in context: Context, completion: @escaping (Timeline<Entry>) -> Void) {
    let entry = loadEntry()
    let nextRefresh = Calendar.current.date(byAdding: .hour, value: 1, to: Date()) ?? Date().addingTimeInterval(3600)
    completion(Timeline(entries: [entry], policy: .after(nextRefresh)))
  }

  private func loadEntry() -> LoveSjEntry {
    let data = UserDefaults(suiteName: widgetGroupId)
    let title = data?.string(forKey: widgetTitleKey) ?? "우리 일정"
    let items = readItems(from: data) ?? ["일정을 불러오는 중이에요."]
    return LoveSjEntry(date: Date(), title: title, items: items)
  }

  private func readItems(from defaults: UserDefaults?) -> [String]? {
    guard
      let raw = defaults?.string(forKey: widgetItemsKey),
      let data = raw.data(using: .utf8),
      let items = try? JSONDecoder().decode([String].self, from: data)
    else {
      return nil
    }

    let filtered = items.filter { !$0.trimmingCharacters(in: .whitespacesAndNewlines).isEmpty }
    return filtered.isEmpty ? nil : filtered
  }
}

struct LoveSjEntry: TimelineEntry {
  let date: Date
  let title: String
  let items: [String]
}

struct LoveSjWidgetEntryView: View {
  let entry: Provider.Entry
  @Environment(\.widgetFamily) private var family

  var body: some View {
    let visibleItems = Array(entry.items.prefix(rowLimit))

    VStack(alignment: .leading, spacing: 10) {
      HStack(alignment: .center, spacing: 8) {
        Text(entry.title)
          .font(.system(size: 17, weight: .bold))
          .foregroundColor(Color(red: 0.23, green: 0.14, blue: 0.19))
          .lineLimit(1)

        Spacer(minLength: 8)

        Text("\(visibleItems.count)")
          .font(.system(size: 11, weight: .semibold))
          .foregroundColor(Color(red: 0.48, green: 0.35, blue: 0.40))
          .padding(.horizontal, 8)
          .padding(.vertical, 4)
          .background(
            Capsule()
              .fill(Color.white.opacity(0.72))
          )
      }

      VStack(alignment: .leading, spacing: 6) {
        ForEach(Array(visibleItems.enumerated()), id: \.offset) { _, item in
          Text(item)
            .font(.system(size: 12, weight: .medium))
            .foregroundColor(Color(red: 0.23, green: 0.14, blue: 0.19))
            .lineLimit(1)
            .frame(maxWidth: .infinity, alignment: .leading)
            .padding(.horizontal, 10)
            .padding(.vertical, 8)
            .background(
              RoundedRectangle(cornerRadius: 12, style: .continuous)
                .fill(Color.white.opacity(0.82))
            )
        }
      }

      Spacer(minLength: 0)
    }
    .padding(12)
    .frame(maxWidth: .infinity, maxHeight: .infinity, alignment: .topLeading)
    .background(widgetBackground)
  }

  private var rowLimit: Int {
    switch family {
    case .systemSmall:
      return 3
    case .systemMedium, .systemLarge:
      return 6
    default:
      return 3
    }
  }

  @ViewBuilder
  private var widgetBackground: some View {
    let background = LinearGradient(
      colors: [
        Color(red: 1.0, green: 0.94, blue: 0.96),
        Color(red: 0.99, green: 0.87, blue: 0.91),
      ],
      startPoint: .topLeading,
      endPoint: .bottomTrailing
    )

    if #available(iOSApplicationExtension 17.0, *) {
      background
        .containerBackground(for: .widget) { background }
    } else {
      background
    }
  }
}

@main
struct LoveSjWidget: Widget {
  let kind: String = "LoveSjWidget"

  var body: some WidgetConfiguration {
    StaticConfiguration(kind: kind, provider: Provider()) { entry in
      LoveSjWidgetEntryView(entry: entry)
    }
    .configurationDisplayName("우리 일정")
    .description("기념일과 다가오는 생일을 한눈에 보여줍니다.")
    .supportedFamilies([.systemSmall, .systemMedium, .systemLarge])
  }
}

struct LoveSjWidget_Previews: PreviewProvider {
  static var previews: some View {
    Group {
      LoveSjWidgetEntryView(
        entry: LoveSjEntry(
          date: Date(),
          title: "우리 일정",
          items: [
            "연애 시작일  2022. 10. 9.  D+1283",
            "결혼기념일  2024. 11. 2.  D+529",
            "수진  2026. 6. 23.  D-71",
          ]
        )
      )
      .previewContext(WidgetPreviewContext(family: .systemSmall))

      LoveSjWidgetEntryView(
        entry: LoveSjEntry(
          date: Date(),
          title: "우리 일정",
          items: [
            "연애 시작일  2022. 10. 9.  D+1283",
            "결혼기념일  2024. 11. 2.  D+529",
            "수진  2026. 6. 23.  D-71",
            "기훈  2026. 7. 1.  D-79",
            "어머니  2026. 8. 3.  D-112",
          ]
        )
      )
      .previewContext(WidgetPreviewContext(family: .systemMedium))
    }
  }
}

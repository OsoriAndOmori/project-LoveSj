import SwiftUI
import WidgetKit

private let widgetGroupId = "group.com.osori.lovesj"

struct Provider: TimelineProvider {
  func placeholder(in context: Context) -> LoveSjEntry {
    LoveSjEntry(date: Date(), title: "다가오는 생일", message: "생일 정보를 불러오는 중")
  }

  func getSnapshot(in context: Context, completion: @escaping (LoveSjEntry) -> Void) {
    let data = UserDefaults(suiteName: widgetGroupId)
    let entry = LoveSjEntry(
      date: Date(),
      title: data?.string(forKey: "title") ?? "다가오는 생일",
      message: data?.string(forKey: "message") ?? "생일 정보를 불러오는 중"
    )
    completion(entry)
  }

  func getTimeline(in context: Context, completion: @escaping (Timeline<Entry>) -> Void) {
    getSnapshot(in: context) { entry in
      let timeline = Timeline(entries: [entry], policy: .atEnd)
      completion(timeline)
    }
  }
}

struct LoveSjEntry: TimelineEntry {
  let date: Date
  let title: String
  let message: String
}

struct LoveSjWidgetEntryView: View {
  var entry: Provider.Entry

  var body: some View {
    VStack(alignment: .leading, spacing: 6) {
      Text(entry.title)
        .font(.headline)
      Text(entry.message)
        .font(.footnote)
    }
    .padding()
    .frame(maxWidth: .infinity, maxHeight: .infinity, alignment: .leading)
  }
}

@main
struct LoveSjWidget: Widget {
  let kind: String = "LoveSjWidget"

  var body: some WidgetConfiguration {
    StaticConfiguration(kind: kind, provider: Provider()) { entry in
      LoveSjWidgetEntryView(entry: entry)
    }
    .configurationDisplayName("다가오는 생일")
    .description("가장 가까운 생일 정보를 보여줍니다.")
  }
}

struct LoveSjWidget_Previews: PreviewProvider {
  static var previews: some View {
    LoveSjWidgetEntryView(
      entry: LoveSjEntry(date: Date(), title: "다가오는 생일", message: "기훈  2026. 6. 23.    D - 140")
    )
    .previewContext(WidgetPreviewContext(family: .systemSmall))
  }
}

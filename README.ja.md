＃ ktor static generation

ktor の特定の route を html ファイルなどに出力するための plugin。
ktor で実装したコンテンツをファイルとして出力することで、静的ホスティングサービスなどへの展開を可能にします。

## 使い方

注意: <your_package> にはあなたのプロジェクトに適するパッケージ名を入れてください。

### 1. build.gradle.kts に plugin の依存関係と static generation の設定を追加する

```kt
// build.gradle.kts
plugins {
    id("me.tbsten.ktor.static.generation") version "0.1.0"
}

dependencies {
    implementation("me.tbsten.ktor:ktor-static-generation:0.1.0")
}

val staticGenerate by tasks.getting(KtorStaticGenerationTask::class) {
    mainClass.set("<your_package>.StaticGenerationKt")
    classpath(sourceSets.main.get().runtimeClasspath)
}
```

### 2. src ディレクトリの <your_package> 配下に StaticGeneration.kt を以下のように作成

configureRouting に あなたのプロジェクトの routing 設定を `routing` 関数を使って設定しているものとします。

```kt
package <your_package>

import kotlinx.coroutines.runBlocking
import me.tbsten.ktor.staticGeneration.generateStatic
import <your_package>.configureRouting

fun main() {
    runBlocking {
        generateStatic {
            configureRouting()
        }
    }
}
```

### 3. static generate 対象の `get("/path/to/route") { }` を `staticGeneration("/path/to/route") { }` に置き換える

```diff
- get("/") {
+ staticGeneration("/") {
-     call.respondText("<h1>Hello World!</h1>", ContentType.Text.Html)
- }
```

もしあなたの route がパラメータを含む場合、以下のように `staticPaths` 引数を指定する必要があります。

```kt
// NG パラメータがあるのにも関わらず、 staticPaths を指定していない
staticGeneration(
    "/blog/{blogId}",
) { ... }

// OK
staticGeneration(
    "/blog/{blogId}",
    staticPaths = { flowOf("/blog/1", "/blog/2", "/blog/3") },
) { ... }
```

### 4. `staticGenerate` gradle task を呼び出す

```shell
./gradlew staticGenerate
```

これであなたのプロジェクトに含まれる `staticGenerate("/path") { }` でマークされたルートが、ファイルとして `build/ktor-static-generate-output/` ディレクトリに出力されます。

## その他

- 現在は GET メソッドのみサポートしています。
- 不明な点・不具合は [issue](https://github.com/TBSten/ktor-static-generation/issues) で質問・報告してください。

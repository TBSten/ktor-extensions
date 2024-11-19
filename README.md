＃ ktor static generation

A plugin for outputting specific ktor routes to html files, etc.
By outputting content implemented with ktor as a file, it enables deployment to static hosting services, etc.

## How to use

Note: <your_package> should be the name of the package appropriate for your project.

### 1. Add plugin dependencies and static generation settings to build.gradle.kts

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

### 2. Create StaticGeneration.kt under <your_package> in the src directory as follows

Assume that you have configured the routing settings for your project in configureRouting using the `routing` function.

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

### 3. Install StaticGeneration to the application

````kt
fun Application.module() {
    install(StaticGeneration)
}

### 4. Replace `get("/path/to/route") { }` with `staticGeneration("/path/to/route") { }` in the static generate target.

```diff
- get("/") {
+ staticGeneration("/") {
-     call.respondText("<h1>Hello World!</h1>", ContentType.Text.Html)
- }
````

If your route contains parameters, you must specify the `staticPaths` argument as follows

```kt
// NG There is a parameter ({blogId}), but staticPaths is not specified.
staticGeneration(
    "/blog/{blogId}",
) { ... }

// OK
staticGeneration(
    "/blog/{blogId}",
    staticPaths = { flowOf("/blog/1", "/blog/2", "/blog/3") },
) { ... }
```

### 4. Call the `staticGenerate` gradle task

```shell
./gradlew staticGenerate
```

Now the routes marked with `staticGeneration("/path") { }` in your project will be output as files in the `build/ktor-static-generate-output/` directory.

## Other

- Currently only the GET method is supported.
- Please ask or report any questions or bugs at [issue](https://github.com/TBSten/ktor-static-generation/issues).

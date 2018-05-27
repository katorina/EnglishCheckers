import javafx.application.Application
import javafx.stage.Stage

class EnglishCheckers : Application() {
    override fun start(primaryStage: Stage?) {
        primaryStage?.scene = Board.create()
        primaryStage?.title = "English Checkers"
        primaryStage?.show()
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            launch(EnglishCheckers::class.java)
        }
    }
}
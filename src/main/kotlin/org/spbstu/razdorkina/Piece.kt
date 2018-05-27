import com.sun.org.apache.xpath.internal.operations.Bool
import javafx.scene.layout.StackPane
import javafx.scene.paint.Color
import javafx.scene.shape.Ellipse

class Piece(val type: PieceType, x: Int, y: Int) : StackPane() {

    var currentX = 0.0
        private set
    var currentY = 0.0
        private set

    private var mouseX = 0.0
    private var mouseY = 0.0

    init {
        move(x * Board.TILE_SIZE, y * Board.TILE_SIZE)

        val ellipse = Ellipse()
        ellipse.radiusX = Board.TILE_SIZE * 0.3
        ellipse.radiusY = Board.TILE_SIZE * 0.3

        ellipse.fill = if (type == PieceType.RED) Color.RED else Color.WHITE

        ellipse.stroke = Color.BLACK
        ellipse.strokeWidth = 2.0

        ellipse.translateX = Board.TILE_SIZE * 0.2
        ellipse.translateY = Board.TILE_SIZE * 0.2

        children.add(ellipse)

        setOnMousePressed {
            mouseX = it.sceneX
            mouseY = it.sceneY
        }

        setOnMouseDragged {
            relocate(it.sceneX - mouseX + currentX, it.sceneY - mouseY + currentY)
        }

        setOnMouseReleased {
            MoveController.makeMove(this, layoutX, layoutY)
        }
    }

    fun move(x: Double, y: Double) {
        currentX = x
        currentY = y
        relocate(x, y)
    }

    fun moveBack() {
        relocate(currentX, currentY)
    }

    fun getDirection() = if (type == PieceType.RED) 1 else -1

    companion object {
        enum class PieceType {
            RED, WHITE
        }
    }
}
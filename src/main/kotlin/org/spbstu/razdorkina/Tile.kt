import javafx.scene.shape.Rectangle
import javafx.scene.paint.Color

class Tile(isWhite: Boolean, x: Int, y: Int) : Rectangle() {
    init {
        width = Board.TILE_SIZE
        height = Board.TILE_SIZE

        relocate(x * Board.TILE_SIZE, y * Board.TILE_SIZE)

        val fill = if (isWhite) Color.WHITE else Color.GRAY
        setFill(fill)
    }
}
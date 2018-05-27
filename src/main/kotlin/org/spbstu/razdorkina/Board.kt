import javafx.scene.Group
import javafx.scene.Scene
import javafx.scene.layout.Pane

object Board {
    const val TILE_SIZE = 100.0
    const val ROWS = 8
    const val COLUMNS = 8

    private val tilesGroup = Group()
    private val piecesGroup = Group()

    private val pieces: Array<Array<Piece?>> = Array(COLUMNS, {
        Array(ROWS, {
            null as Piece?
        })
    })

    fun create(): Scene {
        val root = Pane()
        root.setPrefSize(TILE_SIZE * COLUMNS, TILE_SIZE * ROWS)
        root.children.add(tilesGroup)
        root.children.add(piecesGroup)

        addTiles()
        addRedPieces()
        addWhitePieces()

        return Scene(root)
    }

    private fun addTiles() {
        var y = 0
        var x = 0

        while (y < ROWS) {
            while (x < COLUMNS) {
                val tile = Tile((x + y) % 2 == 0, x, y)
                tilesGroup.children.add(tile)
                x++
            }
            x = 0
            y++
        }
    }

    private fun addRedPieces() {
        val startRedPosition = 2
        val piecesNumber = 12

        for (i in 0 until piecesNumber) {
            var position = startRedPosition + i * 2
            val y = (position - 1) / ROWS
            if (y % 2 == 0) {
                position--
            }
            val x = position % COLUMNS
            val piece = Piece(Piece.Companion.PieceType.RED, x, y)
            piecesGroup.children.add(piece)

            pieces[x][y] = piece
        }
    }

    private fun addWhitePieces() {
        val startWhitePosition = ROWS * COLUMNS - 2
        val piecesNumber = 12

        for (i in 0 until piecesNumber) {
            var position = startWhitePosition - i * 2
            val y = position / ROWS
            if (y % 2 == 0) {
                position++
            }
            val x = position % COLUMNS
            val piece = Piece(Piece.Companion.PieceType.WHITE, x, y)
            piecesGroup.children.add(piece)

            pieces[x][y] = piece
        }
    }

    fun rowFromX(x: Double) = ((x + TILE_SIZE / 2) / TILE_SIZE).toInt()
    fun rowFromY(y: Double) = ((y + TILE_SIZE / 2) / TILE_SIZE).toInt()

    fun hasPiece(x: Int, y: Int) = pieces[x][y] != null
    fun getPiece(x: Int, y: Int) = pieces[x][y]

    fun removePiece(x: Int, y: Int) {
        val piece = getPiece(x, y)
        if (piece != null) {
            pieces[x][y] = null
            piecesGroup.children.remove(piece)
        }
    }

    fun updatePiece(piece: Piece, x: Int, y: Int) {
        val oldX = rowFromX(piece.currentX)
        val oldY = rowFromX(piece.currentY)
        pieces[oldX][oldY] = null
        pieces[x][y] = piece

        piece.move(x * Board.TILE_SIZE, y * Board.TILE_SIZE)
    }
}
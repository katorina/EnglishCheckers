object MoveController {
    private var isWhiteTurn = true

    fun makeMove(piece: Piece, x: Double, y: Double) {
        val row = Board.rowFromX(x)
        val column = Board.rowFromY(y)
        if (isRightTurn(piece) && canMove(piece, row, column)) {
            Board.updatePiece(piece, row, column)
            isWhiteTurn = !isWhiteTurn
        } else {
            piece.moveBack()
        }
    }

    private fun isRightTurn(piece: Piece): Boolean = (piece.type == Piece.Companion.PieceType.WHITE && isWhiteTurn) ||
            (piece.type == Piece.Companion.PieceType.RED && !isWhiteTurn)

    private fun canMove(piece: Piece, x: Int, y: Int): Boolean {
        val oldX = Board.rowFromX(piece.currentX)
        val oldY = Board.rowFromX(piece.currentY)

        if (Board.hasPiece(x, y) || (x + y) % 2 == 0) return false

        if (y - oldY == piece.getDirection() && Math.abs(x - oldX) == 1) return true
        else if (y - oldY == piece.getDirection() * 2 && Math.abs(x - oldX) == 2) {
            val xBetween = oldX + (x - oldX) / 2
            val yBetween = oldY + (y - oldY) / 2
            val pieceBetween = Board.getPiece(xBetween, yBetween)
            if (pieceBetween != null && pieceBetween.type != piece.type) {
                Board.removePiece(xBetween, yBetween)
                return true
            }
        }

        return false
    }
}
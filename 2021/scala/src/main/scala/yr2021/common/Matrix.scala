package yr2021.common


case class Matrix(rows:Vector[Vector[Int]]) {
  def sizeRows: Int = rows.size
  def sizeColumns: Int = rows(0).size
  def *(scalar: Int): Matrix = Matrix(rows.map(row=>row.map(cell=> cell * scalar)))
  def *(other: Matrix): Option[Matrix] = if (sizeColumns == other.sizeRows)
      Some(Matrix(rows.map(row => other.rows.transpose.map(oRow=> (row zip oRow).map(x=> x._1 * x._2).sum))))
    else None
  def toCoordinates: Vector[CoordinateND] = rows.map(CoordinateND(_))
  def resize(r: Int, c: Int): Matrix = {
    val nv = Vector.fill(c)(0)
    Matrix((rows.map(x=>(x++nv).take(c)) ++ Vector.fill(c)(nv)).take(r))
  }
  def updated(r: Int, c: Int, v:Int): Matrix = Matrix((0 until sizeRows).map(i=>
    if (i != r) rows(i) else rows(i).updated(c, v)).toVector)
}

object Matrix {

 def parse(rows:Vector[Vector[Int]]):Option[Matrix] =
   if (rows.size>0 && rows(0).size>0 && rows.forall(_.size == rows(0).size)) Some(Matrix(rows)) else None

  def parse(coordinateND: CoordinateND): Matrix =
    Matrix(Vector(coordinateND.terms))

  def parse(coordinateNDs: Vector[CoordinateND]): Matrix =
    Matrix(coordinateNDs.map(_.terms))

}

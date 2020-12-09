package yr2020.day07

object Day07 {

  case class Bag (name: String, bags: List[Bag])

    val dottedBlack = Bag("Dotted Black", List())
    val fadedBlue = Bag("Faded Blue", List())
    val vibrantPlum = Bag("Vibrant Plum", List(fadedBlue, fadedBlue, fadedBlue, fadedBlue, fadedBlue, dottedBlack, dottedBlack, dottedBlack, dottedBlack, dottedBlack, dottedBlack))
    val darkOlive = Bag("Dark Olive", List(fadedBlue, fadedBlue, fadedBlue, dottedBlack, dottedBlack, dottedBlack, dottedBlack))
    val shinyGold = Bag("Shiny Gold", List(darkOlive, vibrantPlum, vibrantPlum))
    val mutedYellow = Bag("Muted Yellow", List(shinyGold, shinyGold, fadedBlue, fadedBlue, fadedBlue, fadedBlue, fadedBlue, fadedBlue, fadedBlue, fadedBlue, fadedBlue))
    val brightWhite = Bag("Bright White", List(shinyGold))
    val lightRed = Bag("Light Red", List(brightWhite, mutedYellow, mutedYellow))
    val darkOrange = Bag("Dark Orange", List(brightWhite, brightWhite, brightWhite, mutedYellow, mutedYellow, mutedYellow, mutedYellow))
    val allBags = Bag("all bags", List(darkOrange, lightRed, brightWhite, mutedYellow, shinyGold, darkOlive, vibrantPlum, fadedBlue, dottedBlack))

  def locateBag(bag: Bag) = {

  }

  def treeSearchCount(currBag:Bag, targetBag: Bag): Int = {
    if (currBag == targetBag)
      1
    else
      currBag.bags.map{b=>println("--"+b.name);treeSearchCount(b, targetBag)}.sum
  }

  def main(args: Array[String]): Unit = {
    println(allBags.bags.map{b=>println("-"+b.name);treeSearchCount(b, shinyGold)})
  }

}

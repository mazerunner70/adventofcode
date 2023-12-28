from pathlib import Path


class DataLoader(object):
    def __init__(self, adventday ):
        self.adventday = adventday

    def getresourcefile(self, filename):
        return Path.cwd() / "input" / f"day{self.adventday:02d}" / filename

    def readfile(self, filename):
        with self.getresourcefile(filename).open() as f:
            return f.read()
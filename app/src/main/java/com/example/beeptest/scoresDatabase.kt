package com.example.beeptest

class scoresDatabase{

var id:Int=0
var scoredate: String?= null
var level:Int=0
var shuttle:Int=0
var distance:Int=0

    constructor(id:Int, scoredate: String, level: Int,shuttle:Int,distance:Int) {
        this.id=id
        this.scoredate=scoredate
        this.level=level
        this.shuttle=shuttle
        this.distance=distance
    }
}
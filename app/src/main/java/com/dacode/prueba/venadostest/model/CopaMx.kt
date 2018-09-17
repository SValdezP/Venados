package com.dacode.prueba.venadostest.model

data class CopaMx(var dateTime : String? = null,
                  var teamImage : Int? = null,
                  var homeScore : Int? = null,
                  var awayScore : Int? = null,
                  var opponentImage : Int? = null,
                  var opponentName : String? = null,
                  var local : Boolean? = false,
                  var league : String? = null)


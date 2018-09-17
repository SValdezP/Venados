package com.dacode.prueba.venadostest.model

data class Games(var month : String? = null,
                 var dateTime : String? = null,
                 var dayTime : String? = null,
                 var teamImage : Int? = null,
                 var homeScore : Int? = null,
                 var awayScore : Int? = null,
                 var opponentImage : Int? = null,
                 var opponentName : String? = null,
                 var local : Boolean? = false,
                 var league : String? = null)


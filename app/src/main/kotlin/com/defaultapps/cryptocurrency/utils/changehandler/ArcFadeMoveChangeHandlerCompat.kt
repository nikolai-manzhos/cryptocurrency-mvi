package com.defaultapps.cryptocurrency.utils.changehandler

import com.bluelinelabs.conductor.changehandler.FadeChangeHandler
import com.bluelinelabs.conductor.changehandler.TransitionChangeHandlerCompat

class ArcFadeMoveChangeHandlerCompat : TransitionChangeHandlerCompat {

    constructor() : super()

    constructor(vararg transitionNames: String) :
            super(ArcFadeMoveChangeHandler(transitionNames.asList().toTypedArray()), FadeChangeHandler())

}

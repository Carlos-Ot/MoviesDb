package com.arctouch.codechallenge.base


abstract class BasePresenter<V: BaseView> {

     var view: V? = null

     fun attachView(view: V?) {
         this.view = view

         if(view == null) {
             throw Throwable(
                     message = "This presenter doesn't have a view attached to it",
                     cause = InstantiationException())
         }
         this.init()
     }

     abstract fun init()

     abstract fun resume()

     abstract fun pause()

     abstract fun stop()

     abstract fun destroy()
 }
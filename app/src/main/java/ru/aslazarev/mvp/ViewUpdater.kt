package ru.aslazarev.mvp

class ViewUpdater (val view: MainView) {
    val presenter = MainPresenter()

    fun updateView(id: Int) {
        when(id){
            R.id.btn_counter1 -> {
                val nextValue = presenter.counterClick(0)
                view.setButtonText(0, nextValue.toString())
            }
            R.id.btn_counter2 -> {
                val nextValue = presenter.counterClick(1)
                view.setButtonText(1, nextValue.toString())
            }
            R.id.btn_counter3 -> {
                val nextValue = presenter.counterClick(2)
                view.setButtonText(2, nextValue.toString())
            }
        }
    }

}
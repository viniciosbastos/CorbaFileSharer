package client.contracts

interface IMainContract {

    interface IView: IBaseView {

    }

    interface IPresenter: IBasePresenter<IView> {

    }
}
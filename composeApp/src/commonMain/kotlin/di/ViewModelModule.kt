package di

import org.koin.dsl.module
import ui.detail.DetailViewModel
import ui.home.HomeViewModel

val provideviewModelModule = module {
    single {
        HomeViewModel(get())
    }
    single {
       DetailViewModel(get())
    }
}
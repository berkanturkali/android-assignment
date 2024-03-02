package com.arabam.android.assignment.feature.details.model.command

import com.arabam.android.assignment.core.model.Command
import com.arabam.android.assignment.feature.details.viewmodel.DetailFragmentViewModel

data class AddRemoveFavoritesCommand(
    val viewModel: DetailFragmentViewModel,
) : Command {
    override fun execute() {
        val addedToFavorite = viewModel.setFav.value
        if (addedToFavorite) {
            viewModel.removeFromFav(viewModel.getAdvert())
            viewModel.setFav(false)
        } else {
            viewModel.addToFav(viewModel.getAdvert())
            viewModel.setFav(true)
        }
    }
}
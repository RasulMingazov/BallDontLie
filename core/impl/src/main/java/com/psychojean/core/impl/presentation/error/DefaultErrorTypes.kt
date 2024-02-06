package com.psychojean.core.impl.presentation.error

import com.psychojean.core.api.error.ErrorType
import com.psychojean.core.impl.R

object NoConnectionErrorType : ErrorType {
    override val title: Int = R.string.failed_to_update_data
    override val description: Int = R.string.something_went_wrong
    override val snackbar: Int = R.string.failed_to_update_data
    override val button: Int = R.string.refresh
    override val icon: Int = R.drawable.ic_error
}

object GenericErrorType : ErrorType {
    override val title: Int = R.string.something_went_wrong
    override val description: Int = R.string.check_your_internet_connection_or_try_to_use_vpn
    override val snackbar: Int = R.string.something_went_wrong
    override val button: Int = R.string.refresh
    override val icon: Int = R.drawable.ic_error
}
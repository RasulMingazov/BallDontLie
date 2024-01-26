package com.psychojean.core.impl.presentation.error

import com.psychojean.core.impl.R

abstract class ErrorType {

    abstract fun title(): Int

    abstract fun description(): Int

    abstract fun icon(): Int

    abstract fun snackbar(): Int

    abstract fun button(): Int

    object NoConnection : ErrorType() {
        override fun title(): Int = R.string.failed_to_update_data
        override fun description(): Int = R.string.something_went_wrong
        override fun snackbar(): Int = R.string.failed_to_update_data
        override fun button(): Int = R.string.refresh
        override fun icon(): Int = R.drawable.ic_error
    }
    object Generic : ErrorType() {
        override fun title(): Int = R.string.something_went_wrong
        override fun description(): Int = R.string.check_your_internet_connection_or_try_to_use_vpn
        override fun snackbar(): Int = R.string.something_went_wrong
        override fun button(): Int = R.string.refresh
        override fun icon(): Int = R.drawable.ic_error
    }
}

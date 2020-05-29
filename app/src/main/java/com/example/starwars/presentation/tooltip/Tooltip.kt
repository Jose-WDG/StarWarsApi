package com.example.starwars.presentation.tooltip

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.Typeface
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import android.util.TypedValue
import android.view.Gravity
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.annotation.*
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.ViewCompat
import androidx.core.widget.TextViewCompat
import com.example.starwars.R

class Tooltip private constructor(builder: Builder) :
    TooltipBuilder<Tooltip.Builder?>(builder) {

    override fun createContentView(builder: Builder): View {
        val textView = AppCompatTextView(context)
        textView.setTextAppearance(context, builder.textAppearance)
        textView.text = builder.text
        textView.setPadding(builder.padding, builder.padding, builder.padding, builder.padding)
        textView.setLineSpacing(builder.lineSpacingExtra, builder.lineSpacingMultiplier)
        textView.compoundDrawablePadding = builder.drawablePadding
        TextViewCompat.setCompoundDrawablesRelativeWithIntrinsicBounds(
            textView,
            builder.drawableStart,
            builder.drawableTop,
            builder.drawableEnd,
            builder.drawableBottom
        )
        if (builder.textStyle >= 0) {
            textView.typeface = Typeface.create(textView.typeface, builder.textStyle)
        }

        if (builder.maxWidth >= 0) {
            textView.maxWidth = builder.maxWidth
        }

        if (builder.textSize >= 0) {
            textView.setTextSize(TypedValue.TYPE_NULL, builder.textSize)
        }

        builder.textColor?.also {
            textView.setTextColor(it)
        }

        val textViewParams = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT,
            0F
        )

        textViewParams.gravity = Gravity.CENTER
        textView.layoutParams = textViewParams
        val drawable = GradientDrawable()
        drawable.setColor(builder.backgroundColor)
        drawable.cornerRadius = builder.cornerRadius
        ViewCompat.setBackground(textView, drawable)
        return textView
    }

    override fun getArrowColor(builder: Builder) = builder.backgroundColor

    class Builder :
        TooltipBuilder.Builder<Builder> {
        internal var backgroundColor = 0
        internal var textAppearance = 0
        internal var textStyle = 0
        internal var padding = 0
        internal var maxWidth = 0
        internal var drawablePadding = 0
        internal var cornerRadius = 0f
        internal var textSize = 0f
        internal var lineSpacingExtra = 0f
        internal var lineSpacingMultiplier = 1f
        internal var drawableBottom: Drawable? = null
        internal var drawableEnd: Drawable? = null
        internal var drawableStart: Drawable? = null
        internal var drawableTop: Drawable? = null
        internal var text: CharSequence? = null
        internal var textColor: ColorStateList? = null

        constructor(anchorMenuItem: MenuItem) : super(anchorMenuItem)

        constructor(anchorMenuItem: MenuItem, @StyleRes styleId: Int) : super(
            anchorMenuItem,
            styleId
        )

        constructor(
            anchorMenuItem: MenuItem,
            @AttrRes defStyleAttr: Int,
            @StyleRes defStyleRes: Int
        ) : super(anchorMenuItem, defStyleAttr, defStyleRes)

        constructor(anchorView: View) : super(anchorView)

        constructor(anchorView: View, @StyleRes styleId: Int) : super(anchorView, styleId)

        constructor(
            anchorView: View,
            @AttrRes defStyleAttr: Int,
            @StyleRes defStyleRes: Int
        ) : super(anchorView, defStyleAttr, defStyleRes)

        override fun init(
            context: Context,
            anchorView: View,
            @AttrRes detStyleAttr: Int,
            @StyleRes defStyleRes: Int
        ) {
            super.init(context, anchorView, detStyleAttr, defStyleRes)
            val a = this.context.obtainStyledAttributes(
                null,
                R.styleable.Tooltip,
                detStyleAttr,
                defStyleRes
            )

            backgroundColor = a.getColor(R.styleable.Tooltip_backgroundColor, Color.GRAY)
            cornerRadius = a.getDimensionPixelSize(R.styleable.Tooltip_cornerRadius, -1).toFloat()
            padding = a.getDimensionPixelSize(
                R.styleable.Tooltip_android_padding,
                this.context.resources.getDimensionPixelSize(R.dimen.default_tooltip_padding)
            )
            maxWidth = a.getDimensionPixelSize(R.styleable.Tooltip_android_maxWidth, -1)
            drawablePadding =
                a.getDimensionPixelSize(R.styleable.Tooltip_android_drawablePadding, 0)
            drawableBottom = a.getDrawable(R.styleable.Tooltip_android_drawableBottom)
            drawableEnd = a.getDrawable(R.styleable.Tooltip_android_drawableEnd)
            drawableStart = a.getDrawable(R.styleable.Tooltip_android_drawableStart)
            drawableTop = a.getDrawable(R.styleable.Tooltip_android_drawableTop)
            textAppearance = a.getResourceId(R.styleable.Tooltip_textAppearance, -1)
            text = a.getString(R.styleable.Tooltip_android_text)
            textSize = a.getDimension(R.styleable.Tooltip_android_textSize, -1f)
            textColor = a.getColorStateList(R.styleable.Tooltip_android_textColor)
            textStyle = a.getInteger(R.styleable.Tooltip_android_textStyle, -1)
            lineSpacingExtra =
                a.getDimensionPixelSize(R.styleable.Tooltip_android_lineSpacingExtra, 0).toFloat()
            lineSpacingMultiplier =
                a.getFloat(R.styleable.Tooltip_android_lineSpacingMultiplier, lineSpacingMultiplier)
            a.recycle()
        }

        fun setBackgroundColor(@ColorInt color: Int): Builder {
            backgroundColor = color
            return this
        }

        fun setCornerRadius(@DimenRes resId: Int): Builder {
            return setCornerRadius(context.resources.getDimension(resId))
        }

        fun setCornerRadius(radius: Float): Builder {
            cornerRadius = radius
            return this
        }

        fun setPadding(padding: Int): Builder {
            this.padding = padding
            return this
        }

        @Deprecated("")
        fun setPadding(padding: Float): Builder {
            return setPadding(padding.toInt())
        }

        fun setMaxWidth(maxWidth: Int): Builder {
            this.maxWidth = maxWidth
            return this
        }

        fun setDrawablePadding(padding: Int): Builder {
            drawablePadding = padding
            return this
        }

        fun setDrawableBottom(@DrawableRes resId: Int): Builder {
            return setDrawableBottom(
                ResourcesCompat.getDrawable(
                    context.resources,
                    resId,
                    null
                )
            )
        }

        fun setDrawableBottom(drawable: Drawable?): Builder {
            drawableBottom = drawable
            return this
        }

        fun setDrawableEnd(@DrawableRes resId: Int): Builder {
            return setDrawableEnd(ResourcesCompat.getDrawable(context.resources, resId, null))
        }

        fun setDrawableEnd(drawable: Drawable?): Builder {
            drawableEnd = drawable
            return this
        }

        fun setDrawableStart(@DrawableRes resId: Int): Builder {
            return setDrawableStart(
                ResourcesCompat.getDrawable(
                    context.resources,
                    resId,
                    null
                )
            )
        }

        fun setDrawableStart(drawable: Drawable?): Builder {
            drawableStart = drawable
            return this
        }

        fun setDrawableTop(@DrawableRes resId: Int): Builder {
            return setDrawableTop(ResourcesCompat.getDrawable(context.resources, resId, null))
        }

        fun setDrawableTop(drawable: Drawable?): Builder {
            drawableTop = drawable
            return this
        }

        fun setTextAppearance(@StyleRes resId: Int): Builder {
            textAppearance = resId
            return this
        }

        fun setText(@StringRes resId: Int): Builder {
            return setText(context.getText(resId))
        }

        fun setText(text: CharSequence?): Builder {
            this.text = text
            return this
        }

        fun setTextSize(@DimenRes resId: Int): Builder {
            textSize = context.resources.getDimension(resId)
            return this
        }

        fun setTextSize(size: Float): Builder {
            textSize = TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_SP,
                size,
                context.resources.displayMetrics
            )
            return this
        }

        fun setTextColor(@ColorInt color: Int): Builder {
            textColor = ColorStateList.valueOf(color)
            return this
        }

        fun setTextStyle(style: Int): Builder {
            textStyle = style
            return this
        }

        fun setLineSpacing(
            @DimenRes addResId: Int,
            mult: Float
        ): Builder {
            lineSpacingExtra = context.resources.getDimensionPixelSize(addResId).toFloat()
            lineSpacingMultiplier = mult
            return this
        }

        fun setLineSpacing(
            add: Float,
            mult: Float
        ): Builder {
            lineSpacingExtra = add
            lineSpacingMultiplier = mult
            return this
        }

        @Deprecated("")
        fun setTypeface(typeface: Typeface?): Builder {
            return this
        }

        override fun build(): Tooltip {
            return Tooltip(this)
        }
    }
}
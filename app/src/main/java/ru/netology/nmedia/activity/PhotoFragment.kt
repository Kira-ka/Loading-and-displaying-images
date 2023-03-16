package ru.netology.nmedia.activity

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import ru.netology.nmedia.BuildConfig
import ru.netology.nmedia.R
import ru.netology.nmedia.databinding.FragmentPhotoBinding
import ru.netology.nmedia.viewmodel.PostViewModel


class PhotoFragment : Fragment() {

    val viewModel: PostViewModel by viewModels(ownerProducer = ::requireParentFragment)

    private var _binding: FragmentPhotoBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentPhotoBinding.inflate(inflater, container, false)

        viewModel.edited.observe(viewLifecycleOwner) {
            val i = it.attachment!!.url
            Glide.with(binding.plug2)
                .load("${BuildConfig.BASE_URL}/${it.attachment.url}")
                .error(R.drawable.ic_baseline_error_24)
                .timeout(10000)
                .into(binding.plug2)
        }
        return binding.root
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

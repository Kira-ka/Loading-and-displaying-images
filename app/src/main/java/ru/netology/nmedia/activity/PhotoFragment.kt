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
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import ru.netology.nmedia.BuildConfig
import ru.netology.nmedia.R
import ru.netology.nmedia.databinding.FragmentPhotoBinding
import ru.netology.nmedia.viewmodel.PostViewModel


class PhotoFragment : Fragment() {

    var url: String = ""

    val viewModel: PostViewModel by viewModels(ownerProducer = ::requireParentFragment)

    private var _binding: FragmentPhotoBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            url = it.getString(PostUrl).toString()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentPhotoBinding.inflate(inflater, container, false)

        Glide.with(binding.plug2)
            .load("${BuildConfig.BASE_URL}/media/${url}")
            .error(R.drawable.ic_baseline_error_24)
            .onlyRetrieveFromCache(true)
            .into(binding.plug2)

        binding.topAppBar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }


        return binding.root
    }

    companion object {
        const val PostUrl = "POST_URL"
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

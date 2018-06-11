package digimartapp.gericass.com.feelmucicmock


import android.os.Bundle
import android.os.Handler
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.yuyakaido.android.cardstackview.CardStackView
import com.yuyakaido.android.cardstackview.SwipeDirection
import digimartapp.gericass.com.feelmucicmock.Track.Track
import digimartapp.gericass.com.feelmucicmock.Track.TrackCardAdapter
import digimartapp.gericass.com.feelmucicmock.databinding.FragmentMainBinding
import java.util.ArrayList

class MainFragment : Fragment() {

    var mViewmodel: MainViewModel? = null
    lateinit var cardStackView: CardStackView
    lateinit var cardAdapter: TrackCardAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_main, container, false)
        val viewDataBinding = FragmentMainBinding.bind(view)
        viewDataBinding.viewmodel = mViewmodel
        cardStackView = view.findViewById(R.id.activity_main_card_stack_view) as CardStackView
        setup()
        reload()
        return view
    }

    private fun setup() {
        cardStackView.setCardEventListener(object : CardStackView.CardEventListener {
            override fun onCardDragging(percentX: Float, percentY: Float) {
                Log.d("CardStackView", "onCardDragging")
            }

            override fun onCardSwiped(direction: SwipeDirection) {
                Log.d("CardStackView", "onCardSwiped: " + direction.toString())
                Log.d("CardStackView", "topIndex: " + cardStackView.getTopIndex())
                if (cardStackView.topIndex == cardAdapter.count - 5) {
                    Log.d("CardStackView", "Paginate: " + cardStackView.getTopIndex())
                    paginate()
                }
            }

            override fun onCardReversed() {
                Log.d("CardStackView", "onCardReversed")
            }

            override fun onCardMovedToOrigin() {
                Log.d("CardStackView", "onCardMovedToOrigin")
            }

            override fun onCardClicked(index: Int) {
                Log.d("CardStackView", "onCardClicked: $index")
            }
        })
    }

    private fun createTouristSpots(): List<Track> {
        val tracks = ArrayList<Track>()
        tracks.add(Track("Paramore - Riot!", "https://images-na.ssl-images-amazon.com/images/I/91b6aYwjwjL._SL1425_.jpg", "エモい"))
        tracks.add(Track("Nirvana - Nevermind", "https://images-na.ssl-images-amazon.com/images/I/71DQrKpImPL._SL1400_.jpg", "カートコバーンカッコ良い"))
        tracks.add(Track("kz(livetune) feat. Hatsune Miku - Tell Your World", "https://images-na.ssl-images-amazon.com/images/I/61Fp-iEmc2L.jpg", "kz天才だなあ"))
        tracks.add(Track("Shlohmo - Dark Red", "https://images-na.ssl-images-amazon.com/images/I/91iU%2BZG1uvL._SY355_.jpg", "dope"))
        tracks.add(Track("Clark - Clark", "https://images-na.ssl-images-amazon.com/images/I/81bG%2BeD2U5L._SY355_.jpg", "金属っぽくて最高"))
        tracks.add(Track("Lapalux - Ruinism", "http://www.indienative.com/wp-content/uploads/2017/04/600x600bb-2.jpg", "悪すぎる"))
        tracks.add(Track("ichika - forn", "https://images-fe.ssl-images-amazon.com/images/I/61EciT7R69L._SS500.jpg", "クリーン綺麗すぎ"))
        tracks.add(Track("坂本龍一 - async", "https://images-na.ssl-images-amazon.com/images/I/81TaA20kFAL._SX355_.jpg", "脳に直接くる"))
        tracks.add(Track("Cerrone - Red Lips", "https://d38fgd7fmrcuct.cloudfront.net/1_3kgkijvpsbn8lgowsjq9l.jpg", "リリースしてから音沙汰ねえな"))
        tracks.add(Track("フィロソフィーのダンス - ザ・ファウンダー", "https://images-na.ssl-images-amazon.com/images/I/A16pzKFRLaL._SY355_.jpg", "アイドルファンク最高ォ〜"))
        return tracks
    }

    private fun createTouristSpotCardAdapter(): TrackCardAdapter {
        val adapter = TrackCardAdapter(activity!!.applicationContext)
        adapter.addAll(createTouristSpots())
        return adapter
    }

    private fun paginate() {
        cardStackView.setPaginationReserved()
        cardAdapter.addAll(createTouristSpots())
        cardAdapter.notifyDataSetChanged()
    }

    private fun reload() {
        cardStackView.visibility = View.GONE
        Handler().postDelayed({
            cardAdapter = createTouristSpotCardAdapter()
            cardStackView.setAdapter(cardAdapter)
            cardStackView.visibility = View.VISIBLE
        }, 1000)
    }

}

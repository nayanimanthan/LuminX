package com.example.luminx.ui.learn;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.luminx.R;
import com.example.luminx.databinding.FragmentLearnBinding;

// import com.example.luminx.databinding.FragmentDashboardBinding;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class LearnFragment extends Fragment implements LearnListAdapter.ItemClickListener {

    private FragmentLearnBinding binding;
    ArrayList<LearnModel> learnlist = new ArrayList<>();
 //   int[] images = new int[]{R.drawable.image1, R.drawable.image2, R.drawable.image3, R.drawable.image4};
    String[] title = new String[]{"Reduce the use of decorative lighting",
            "Use of covered bulbs that light facing downwards",
            "Use LED Light",
            "Minimizing the use of lights",
            "The use of automatic systems to turn off street light at certain times",
            "Have all information and facts about light pollution",
            "The development and advancement of better alternatives to cruises, lighthouses and ships",
            "Refrain from light trespassing",
            "Preventive measures are always important",
            "Glare-free lighting for vehicles driven at night",
            "Check and put to stop the use of needless lighting during the day",
            "All outdoor lights with glare should be replaced with low-glare alternatives",
            "Use of motion sensors on important outdoor lights",
            "Colored lights can be used as an option",
            "Try to turn your lights off!",
            "Use Glow stones for all your Outdoor Night Lighting",
            "Avoid blue lights at night",
            "Purchase IDA Approved light fixtures",
            "Support wilderness",
            "Get involved"};
    String[] desciption = new String[]{"Festivities and celebration periods often leads to the excessive use of decorative lighting kept on all day and night. The use of decorative lighting during festive seasons should thus be lessened to reduce the brightening of the skies. As an alternative, environmentally friendly candles should be used for the celebration periods. Furthermore, it will also help in conserving energy.",
            "For street lights – streets and highways lighting must be properly designed such that too much light is not reflected in the sky. The solution for this is designing covered bulbs that light facing downwards, and for this reason, it would help reduce the brightening of the skies as it eliminates the light that reflects into the sky.",
            "LEDs and compact fluorescents (CFLs) can help reduce energy use and protect the environment, but only warm-colored bulbs should be used. Switching to LED lighting allows for reduced luminance without compromising visibility. IDA recommends that only warm light sources be used for outdoor lighting. This includes Low-pressure Sodium (LPS), High-pressure Sodium (HPS) and low-color-temperature LEDs. Use “warm” or filtered LEDs (CCT < 3,000 K; S/P ratio < 1.2) to minimize blue emission.",
            "Switching off unnecessary lights can hugely help in reducing light pollution. This is the cheapest, easiest and most effective method of dealing with the problem. It can be achieved by turning off lights when about to sleep or whenever there is no activity going on outside is the house. If the concern is security, then lights should only be put on if there is a security alarm.",
            "In the daytime, there is no need for street lighting. Also, when the moon shines bright, there is no need for street lighting. Automated timers and systems can be used to turn off street and highway lights when they are not needed to encourage natural lighting. They can be pre-set to turn off automatically whenever the environment is naturally bright.",
            "Having knowledge about the sources and effects of light pollution can significantly aid in dealing with the problem. The overriding recommendation is extensive awareness creation as most people don’t even know about light pollution.It is, therefore, the moral duty of those aware of the situation to spread the word about light pollution and its remedies just as this article does. If you happen to read this, you can share with as many people as possible in order for more people to be aware of light pollution.",
            "In the marine world, lighthouses are used for navigation purposes for boats, cruises and ships. The drawback is that the lighthouses emit very powerful lights that cause light pollution in the marine world, directly affecting the habitats of aquatic species. Accordingly, scientists are urged to research the issue and develop better and environmentally friendly alternatives that can be used for navigation purposes.",
            "The use of bright outdoor lights directed towards neighboring houses is completely unethical because it creates discomfort. Therefore, it must be ensured that outdoor lights do not trespass into residential houses. It is as simple as ensuring the lighting in your house or street lighting projects does not allow light to enter the neighboring houses. It is annoying and is even associated with creating sleep disorders. In short, refrain from using outdoor lights that glare at the neighbor’s window.",
            "Preventive measures are essential and have to be taken whenever possible to reduce light pollution. Examples include the use of glare-free bulbs, installing low hanging bulbs, having the lights facing downwards, and covering the bulbs to reduce bright skies at night.",
            "Dim lighting while driving at night is just enough for streets and highways already lit with artificial lighting. In areas without artificial lighting, medium lighting is more than enough because it lights up the path or roadway very well.Bright light, on the other hand, causes glare, which may blind oncoming drivers and even interfere with wildlife habitats by altering their natural cycles and operations. Hundreds of wildlife such as deer and zebras are, for instance, killed on the roads in the evenings since the glares blind and distort their night locomotive aspects.",
            "The use of lighting during the day is needless and should be completely avoided. It is simply a waste of energy. Natural light sources should mostly be used to cater to all lighting needs, be it on the streets, in showrooms, in the house, or in offices.",
            "There are many alternatives in the market offering sky-friendly outdoor lighting. It’s important for municipalities, real estate contractors, and individual homeowners to opt for certified low-glare fixtures, which guarantees low-pollution lighting.",
            "Instead of keeping lights on during the night for security reasons, the installation of motion sensors on the lights can prove helpful. Motion sensors will only have the lights on when motion is detected, thus reducing the overall costs on electricity while at the same time cutting back on light pollution at night. Dimmers and timers can also help to reduce average illumination levels and save even more energy.",
            "Colored lights are anti-glare and still serve the purpose of night lighting very well. Yellow, red and amber lights can reduce the negative effects of lighting during the night because they don’t affect night time vision.",
            "The easiest way to help reduce light pollution is to turn your lights off! Not only does this help reduce light pollution, but it also reduces your energy bill and carbon emissions, as well as revealing the beauty of our world in darkness.During power outages in cities, the beautiful night sky can be seen just as it would be out in nature. Light pollution is an easy fix compared to persistent pollutants! Help darken your little part of the sky by turning off your indoor and outdoor lights in the evening, turn off unnecessary indoor lighting particularly in empty office buildings at night and using minimal lighting if nighttime illumination is needed.",
            "Glow stones only emit 5-7 candelas of light and do not ‘cast’ light as electric lights do. The ambient glow from these stones is not a source of light pollution and does not contribute to bright skies at night.These stones can be used to line pathways, steps, and more outdoors instead of bright electric lights. The best part is they do not break and do not require technical expertise to use. Reduce your resource and light pollution impact by choosing them.",
            "Blue-rich white light sources are also known to increase glare and compromise human vision, especially in the aging eye. These lights create potential road safety problems for motorists and pedestrians alike. In natural settings, blue light at night has been shown to adversely affect wildlife behavior and reproduction, particularly in cities, which are often stopover points for migratory species.Outdoor lighting with strong blue content is likely to worsen skyglow because it has a significantly larger geographic reach than lighting consisting of less blue.",
            "The International Dark Sky Association certifies dark sky friendly light fixtures that meet their rigorous guidelines. Look for the IDA symbol when you are purchasing new lights. The Fixture Seal of Approval provides objective, third-party certification for luminaires that minimize glare, reduce light trespass, and don’t pollute the night sky.",
            "Keep exterior lights off as much as possible as they can interfere with the body clocks of nocturnal creatures like salamanders, giving them fewer hours to scavenge for food. And keep interior light indoors with blackout curtains or if you live in a multistory building, use blackout blinds at night, so birds aren’t fatally attracted to your windows. Also, talk with your building manager or tenant’s association about turning your high-rise into a Bird-Friendly Building.",
            "Your contribution as an individual is to get involved in the fight against night pollution. You must be curious to know what your local government or community is doing to reduce light pollution. The simplest way is joining community programs and important political debates about pollution then educate people on the sources, effects and solutions of light pollution."};
    private LearnListAdapter adapter;
    RecyclerView rv_learn;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        LearnViewModel learnViewModel =
                new ViewModelProvider(this).get(LearnViewModel.class);

        binding = FragmentLearnBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        rv_learn = binding.rvLearn;
        getdata();

        return root;
    }

    private void getdata() {
        learnlist = new ArrayList<>();
        for (int i = 0; i < title.length; i++) {
            LearnModel learnModel = new LearnModel();
            learnModel.setTitle(title[i]);
            learnModel.setDesciption(desciption[i]);

            learnlist.add(learnModel);
        }

        adapter= new LearnListAdapter(getActivity(),learnlist);
        adapter.setClickListener(this);
        rv_learn.setLayoutManager(new LinearLayoutManager(getActivity()));
        rv_learn.setAdapter(adapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onItemClick(View view, int position) {
        Intent intent=new Intent(getActivity(),LearnActivity.class);
        intent.putExtra("title",learnlist.get(position).getTitle());
        intent.putExtra("discription",learnlist.get(position).getDesciption());
        startActivity(intent);
    }
}
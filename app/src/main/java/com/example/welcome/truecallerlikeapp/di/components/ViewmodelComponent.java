package com.example.welcome.truecallerlikeapp.di.components;

import com.example.welcome.truecallerlikeapp.di.scopes.ViewmodelScope;
import com.example.welcome.truecallerlikeapp.viewModel.EachLogViewModel;

import dagger.Component;

@ViewmodelScope
@Component(dependencies = ApplicationComponent.class)
public interface ViewmodelComponent {
    void injectViewModel(EachLogViewModel eachLogViewModel);
}

import pandas as pd

import matplotlib.pyplot as plt

from sklearn.model_selection import train_test_split

from sklearn.model_selection import GridSearchCV

from sklearn.preprocessing import MinMaxScaler
from sklearn.metrics import mean_squared_error, r2_score
from sklearn.linear_model import ElasticNet


# read csv
df = pd.read_csv('input.csv',sep=',',encoding='utf-8').drop('Repository_Name', axis=1)

# scale (ElasticNet supports normalization as a hyperparameter)
# scaler = MinMaxScaler()
# df[['Avg_Degree_Centrality_Top','Group_Betweenness_Centrality','Avg_Betweenness_Osc_Top','Percentage_Connected_Actors',
#     'Avg_Sentiment','Avg_Sentiment_Top','Avg_complextiy','Avg_complexity_Top','Avg_Influence_Top',
#     'Avg_Contribution_Index_Top','Percentage_Isolated_Actors','Percentage_Closed_Issues']] = scaler.fit_transform(
# df[['Avg_Degree_Centrality_Top','Group_Betweenness_Centrality','Avg_Betweenness_Osc_Top','Percentage_Connected_Actors',
#     'Avg_Sentiment','Avg_Sentiment_Top','Avg_complextiy','Avg_complexity_Top','Avg_Influence_Top',
#     'Avg_Contribution_Index_Top','Percentage_Isolated_Actors','Percentage_Closed_Issues']]
# )

# train test split
train, test = train_test_split(df, test_size=0.2)
X_train = train.drop('Target', axis=1)
y_train = train.loc[:,['Target']]
X_test = test.drop('Target', axis=1)
y_test = test.loc[:,['Target']]

# create and train model
parameters = {
    'alpha': [1,2,5,10],
    'l1_ratio': [0,0.25,0.5,0.75,1],
    'normalize':[True]
}
regr = GridSearchCV(estimator=ElasticNet(), param_grid=parameters, n_jobs=-1, cv=5)
regr.fit(X_train, y_train)
print('Best score:', regr.best_score_)

# predict and evaluate
y_pred = regr.predict(X_test)
mse = mean_squared_error(y_test, y_pred)
r2 = r2_score(y_test, y_pred)

# Plot outputs
x_column = 'Avg_Sentiment'
plot_X = X_test.loc[:,'Avg_Sentiment']
fig = plt.figure()
ax = fig.add_subplot(111)
ax.scatter(plot_X, y_test,  color='black')
ax.scatter(plot_X, y_pred, color='blue')
ax.set_xlabel(x_column)
ax.set_ylabel('Repository Stars divided by Pull Requests')
ax.set_title('Predictions')

plt.show()

print('Coefficients: ')
print(regr.best_estimator_.coef_)
(ns webtracking-basics.pages
  (:require [ring.util.anti-forgery :as csrf]
            [clojure.tools.logging :as log]            
            [hiccup.core :refer :all]
            [hiccup.page :as page]
            [hiccup.form :as form]
            ;; Project-internal requires
            [webtracking-basics.sqlite :refer [insert-into-db]]))

(defn index [request]
  (page/html5
   (html
    [:head
     [:title "Webtracking Basics"]
     [:link {:rel "stylesheet" :type "text/css" :href "main.css"}]
     [:script {:type "application/javascript" :src "tracking.js" :async true}]])
   (html
    [:body
     [:header
      [:h1 "Webtracking 101"]]
     [:div {:class "content"}
      [:h2 "Heading 1"]
      [:p "I know you are, but what am I?"]
      [:h2 "Heading 2"]
      [:h3 "Heading 2.1"]
      [:p "This is a copy!"]
      [:h3 "Heading 2.2"]
      [:p "Is this a copy?"]
      [:h2 "Heading 3"]
      [:a {:href "about"} "Tell me about you!"]
      [:h2 "Heading 4"]
      [:a {:href "form"} "Let me talk!"]
      [:h2 "Heading 5"]
      [:button {:id "button_1"} "This is a button"]
      [:h2 "Heading 6"]
      [:button {:id "button_2"} "This is another button"]]
     [:footer {:class "footer"}
      [:div]
      [:div {:class "tagline"} "Ég gaf ykkur von sem varð að vonbrigðum … þetta er ágætis byrjun"]]])))

(defn about [request]
  (page/html5
   (html
    [:head
     [:title "About us"]
     [:link {:rel "stylesheet" :type "text/css" :href "main.css"}]
     [:script {:type "application/javascript" :src "tracking.js" :async true}]])
   (html
    [:body
     [:header
      [:h1 "About us"]]
     [:div {:class "content"}
      [:h2 "Who are we?"]
      [:p "Web Tracking Specialists"]
      [:p "WeTrackYou" [:br] "Web Tracking Ave" [:br] "Trackmania, CA 94043"]
      [:h2 "What do we do?"]
      [:p
       [:ul
        [:li "Web Tracking"]
        [:li "Web Analysis"]
        [:li "Web Tracking Analysis"]
        [:li "Web Analysis Tracking"
         [:ul
          [:li "Tracking Tracking"]
          [:li "Analysis Analysis"]]]
        [:li "Enablement"
         [:ul
          [:li "Web Tracking"]
          [:li "Web Tracking"]
          [:li "HTML"]
          [:li "CSS"]
          [:li "Web Tracking"]]]
        [:li "Infrastructure"
         [:ul
          [:li "Internet Information Services"]
          [:li "AOLserver"]
          [:li "Analytics-as-a-Service"]]]]]
      [:h2 "Is that all?"]
      [:p "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Pellentesque non molestie ex, dignissim euismod purus. Morbi porta lectus at enim vulputate, a mattis enim pharetra. Phasellus libero libero, rutrum nec vehicula sed, tristique ac lorem. Nunc nec elit odio. Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Maecenas maximus elementum dui, ac fringilla velit consectetur ac. Curabitur sed est euismod lectus condimentum molestie. Suspendisse iaculis ex eu fringilla blandit. Morbi eget lectus elit. Morbi urna diam, sollicitudin viverra mattis quis, ornare molestie purus. In hac habitasse platea dictumst. Fusce lobortis dolor eget aliquam sagittis."]
      [:p "Curabitur dapibus gravida sapien, et cursus felis ullamcorper vehicula. Sed nulla odio, sagittis at erat non, sodales euismod lacus. Sed vestibulum, lacus ut placerat dapibus, odio nulla facilisis lacus, a pretium eros risus non enim. Integer et neque a elit porttitor viverra a in quam. Mauris accumsan ex faucibus nisl fringilla fermentum. Proin ut accumsan ante. Quisque rutrum ex sit amet sollicitudin placerat. Proin ac est purus. Quisque at erat ligula. Nulla facilisi. Donec ornare leo ac feugiat porta. Cras convallis vel dui eget consectetur."]]
     [:footer {:class "footer"}
      [:div
       [:a {:href "/"} "Return to index"]]
      [:div {:class "tagline"} "Ég gaf ykkur von sem varð að vonbrigðum … þetta er ágætis byrjun"]]])))

(defn form [req]
  (page/html5
   (html
    [:head
          [:title "Forms 101"]
          [:link {:rel "stylesheet" :type "text/css" :href "main.css"}]
          [:script {:type "application/javascript" :src "tracking.js" :async true}]])
   (html
    [:body
     [:header
      [:h1 "Forms 101"]]
     
     [:div {:class "content"}
      [:h2 "Input"]
      (form/form-to [:post ""]
                    [:div.form-title "I say:"]
                    [:div.form-field (form/text-field {:chars 78 :placeholder "..."} "user")]
                    [:div.form-button (form/submit-button "Submit")]
                    (csrf/anti-forgery-field))
      [:h2 "Output"]
      [:p "You said: " [:b {:style "color:red;"} (-> req :params :user h)]]]
     [:footer {:class "footer"}
      [:div
       [:a {:href "/"} "Return to index"]]
      [:div {:class "tagline"} "Ég gaf ykkur von sem varð að vonbrigðum … þetta er ágætis byrjun"]]])))

(defn tracking [request]
  (let [params (-> request :params)]
    (log/info params)
    ;; (insert-into-db {:timestamp (new java.util.Date) :event (str params)})
    (format "Successfully tracked: %s%n" (-> params :event))))

